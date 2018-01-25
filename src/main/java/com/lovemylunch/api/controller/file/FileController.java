package com.lovemylunch.api.controller.file;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.common.beans.ApiCallResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@SpringBootApplication
//@RequestMapping(value={"/{center}"})
@Api(tags = {"file"}, description = "File APIs")
public class FileController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(FileController.class);

    @ApiOperation(value="file upload", notes="",response = String.class)
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public ResponseEntity<ApiCallResult> upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        logger.info("invoke: " + "/file/upload");
        ApiCallResult result = new ApiCallResult();
        try{
            String path = request.getSession().getServletContext().getRealPath("/UploadFile");
            String fileName = file.getOriginalFilename();

            logger.info("fileName=" + fileName);

            String saveFileName = "";
            String suffix = "";
            if(fileName.indexOf(".")>= 0){

                int  indexdot =  fileName.indexOf(".");
                suffix =  fileName.substring(indexdot);

                long now = System.currentTimeMillis();
                saveFileName =  now  + suffix;

                logger.info("saveFileName=" + saveFileName);
            }

            File targetFile = new File(path, saveFileName);

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            file.transferTo(targetFile);

            //String virtualPath = request.getContextPath() + "/UploadFile/" + saveFileName;
            String virtualPath = "/UploadFile/" + saveFileName;
            result.setContent(virtualPath);

            if (null == result.getMessage()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            result.setMessage("upload file failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
