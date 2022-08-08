package com.test.auth.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class EncDecBean implements TypeHandler<String>{

	@Autowired
	EncryptFactory enc;
	
	@Autowired
	DecryptFactory dec; 
	
	public String decodeStr(String str) throws Exception {
		return dec.getFactory().decode(str);
	}
	
	public String encodeStr(String str) throws Exception {
		return enc.getFactory().encode(str);
	}
	
	
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		
		parameter = enc.getFactory().encode(parameter);
		
		ps.setString(i, parameter);
	}

	
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		
		return dec.getFactory().decode(value);
	}

	
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		String value = rs.getString(columnIndex);

		return dec.getFactory().decode(value);
	}

	
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		
		return dec.getFactory().decode(value);
	}

}
