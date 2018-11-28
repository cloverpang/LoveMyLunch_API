/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with AsiaInspection. This 
 * information shall not be distributed or copied without written 
 * permission from the AsiaInspection.
 *
 ***************************************************************************/

package com.lovemylunch.api.dao.mybatis.mapper;

/***************************************************************************
 * <PRE>
 *  Project Name    : psi-service
 * 
 *  Package Name    : com.ai.inspection.dao.mybatis.mapper
 * 
 *  File Name       : ConflictSupportMapper.java
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
public interface ConflictSupportMapper<T> {
	/**
	 * Get an record according to the specified id.
	 */
	public T get(String id);
}
