/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with AsiaInspection. This 
 * information shall not be distributed or copied without written 
 * permission from the AsiaInspection.
 *
 ***************************************************************************/

package com.lovemylunch.api.dao.mybatis.mapper;

import com.lovemylunch.common.beans.ConflictSupport;

import java.util.Date;

/***************************************************************************
 * <PRE>
 *  Project Name    : psi-service
 * 
 *  Package Name    : com.ai.inspection.dao.mybatis.mapper
 * 
 *  File Name       : ConflictHandleMapper.java
 * 
 *  Creation Date   : Feb 3, 2015
 * 
 *  Author          : Hysun He
 * 
 *  Purpose         : TODO
 * 
 * 
 *  History         : TODO
 * 
 * </PRE>
 ***************************************************************************/
public interface ConflictHandleMapper {
	/**
	 * Get the latest updated time according to table name.
	 */
	public Date latestUpdatedTime(ConflictSupport supportBean);

	/**
	 * get total of record according to the specified id.
	 */
	public int count(ConflictSupport supportBean);
}
