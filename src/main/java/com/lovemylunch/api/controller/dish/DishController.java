package com.lovemylunch.api.controller.dish;

import com.lovemylunch.api.controller.BaseController;
import com.lovemylunch.api.service.DishService;
import com.lovemylunch.common.beans.ApiCallResult;
import com.lovemylunch.common.beans.PageBean;
import com.lovemylunch.common.beans.annotation.PermssionSecured;
import com.lovemylunch.common.beans.annotation.TokenSecured;
import com.lovemylunch.common.beans.food.Dish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
@RequestMapping(value={"/{center}"})
@Api(tags = {"dish"}, description = "Dish APIs")
public class DishController extends BaseController{
    protected Logger logger = LoggerFactory.getLogger(DishController.class);

    @Autowired
    private DishService dishService;

    @TokenSecured
    @PermssionSecured(value="dish_get_one")
    @ApiOperation(value="get Dish ", notes="",response = Dish.class)
    @RequestMapping(value={"/dish/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable String center,@PathVariable("id") String id){
        logger.info("invoke: " + "/dish/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Dish dish = dishService.get(id);
            if (null != dish) {
                result.setContent(dish);
            } else {
                result.setMessage("can not found out this dish ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get dish by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dish_get_list")
    @RequestMapping(value={"/dishs"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search dish API", response = Dish.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@PathVariable String center,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/dishs");
        ApiCallResult result = new ApiCallResult();
        try{
            conditionsStr = conditionsStr + "$operationCenterCode::=::" + center;
            PageBean<Dish> dishPageBean = dishService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(dishPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search dish failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dish_add")
    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    @ApiOperation(value = "Create dish API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createDish(@PathVariable String center,
            @RequestBody Dish dish) {
        logger.info("invoke: " + "/dish/");
        ApiCallResult result = new ApiCallResult();
        try{
            dish.setOperationCenterCode(center);
            Boolean excute = dishService.insert(dish);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create dish failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dish_update")
    @RequestMapping(value = "/dish", method = RequestMethod.PUT)
    @ApiOperation(value = "Save dish API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveDish(@PathVariable String center,
            @RequestBody Dish dish) {
        logger.info("invoke: " + "/dish/");
        ApiCallResult result = new ApiCallResult();
        try{
            dish.setOperationCenterCode(center);
            Boolean excute = dishService.update(dish);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save dish failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dish_delete")
    @RequestMapping(value = "dish/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete dish API", response = boolean.class)
    public ResponseEntity<ApiCallResult> deleteDish(@PathVariable String center,
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/dish/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = dishService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete dish failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="dish_marknotuse")
    @RequestMapping(value = "dish/markNotUse/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "MarkNotUse dish API", response = boolean.class)
    public ResponseEntity<ApiCallResult> makeNotUse(@PathVariable String center,
            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/dish/markNotUse/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = dishService.markeNotOpen(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("MarkNotUse dish failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
