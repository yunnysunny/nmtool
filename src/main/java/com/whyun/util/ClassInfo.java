package com.whyun.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ClassInfo {
	public static final String show(Object obj) throws Exception {
		String str = "{";
		Class<?> classz = obj.getClass();
		Field[] fields = classz.getDeclaredFields();

		for (int i=0,len=fields.length;i<len;i++) {
			Field f = fields[i];
			String nameField = f.getName();
			String firstChar = nameField.substring(0, 1);
			String methodField = nameField.replaceFirst(firstChar, firstChar.toUpperCase());
			Method getMethod;
			try {
				getMethod = classz.getDeclaredMethod("get" + methodField);
				String nameValue = "";
				try {
					Object returnObj = getMethod.invoke(obj, new Object[0]);
					if (returnObj != null) {
						nameValue = returnObj.toString();						
					}
					if (getMethod.getReturnType() == String.class) {
						nameValue = "\"" + nameValue + "\"";
					}
					
				} catch (IllegalArgumentException e) {
					throw e;
				} catch (IllegalAccessException e) {
					throw e;
				} catch (InvocationTargetException e) {
					throw e;
				}
				
				str += nameField + ":" + nameValue;
				if (i != len -1 ) {
					str += ",";
				}
			} catch (SecurityException e1) {
				throw e1;
			} catch (NoSuchMethodException e1) {
				throw e1;
			}
			
		}
		str += "}";
		return str;
	}
}
