package accessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyAccessor {
	private Method getter;
	private Method setter;
	private Object object;

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
		this.setSetterFromGetter();
	}

	public Boolean doSet(Object newValue) {
		if(setter == null) return Boolean.FALSE;
		try {
			setter.invoke(object, newValue);
			return Boolean.TRUE;
		} catch (IllegalArgumentException e) {
			return Boolean.FALSE;
		} catch (IllegalAccessException e) {
			return Boolean.FALSE;
		} catch (InvocationTargetException e) {
			return Boolean.FALSE;
		}
	}
	public Object doGet() {
		try {
			return getter.invoke(object);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isGetter(Method method) {
		String name = method.getName();
		String accessName;
		
		if(name.startsWith("get"))
			accessName = "get";
		else if(name.startsWith("is"))
			accessName = "is";
		else return false;
		
		int length = accessName.length();
		
		return (name.length() > length && isCap(name, length));
	}
	public static boolean isSetter(Method method) {
		String name = method.getName();
		int length = "set".length();
		return (name.startsWith("set") && name.length() > length && isCap(name, length));
	}
	private static boolean isCap(String text, int atIndex) {
		return Character.isUpperCase(text.charAt(atIndex));
		
	}
	public static Method getSetterOfGetter(Method getter, Class<?> fromClass) {
		if(!isGetter(getter)) return null;
		
		String setName = "set"+getVariableName(getter, true);
		try {
			Method setter = fromClass.getMethod(setName, getter.getReturnType());

			return setter;
		} catch (SecurityException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		}
	}
	public void setSetterFromGetter() {
		setter = getSetterOfGetter(getter, object.getClass());
	}
	public static String getVariableName(Method method, Boolean methodName) {
		String name = method.getName();
		if(isSetter(method)) {
			name = name.substring("set".length());
		}
		else if(isGetter(method)) {
			if(name.startsWith("get"))
				name = name.substring("get".length());
			else
				name = name.substring("is".length());
		}
		else return "";
		
		if(methodName) return name;
		
		if(name.length() == 1) return name.toLowerCase();
		if(isCap(name, 1)) {
			return name;
		}
		return Character.toString(Character.toLowerCase(name.charAt(0)))+name.substring(1);
	}
	
	/**
	 * @return the getter
	 */
	public Method getGetter() {
		return getter;
	}
	/**
	 * @param getter the getter to set
	 */
	public void setGetter(Method getter) {
		this.getter = getter;
	}
	/**
	 * @return the setter
	 */
	public Method getSetter() {
		return setter;
	}
	/**
	 * @param setter the setter to set
	 */
	public void setSetter(Method setter) {
		this.setter = setter;
	}
	
	public final Class<?> getObjectClass() {
		return getter.getReturnType();
	}
}
