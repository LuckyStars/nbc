package com.xuechong.bootstraptests.py.factory;

import org.apache.log4j.Logger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class PyFactoryUtil {
	
	private static final Logger logger = Logger.getLogger(PyFactoryUtil.class);
	
	@SuppressWarnings("rawtypes")
	static Object getByClass(Class clazz){
		return getByClass(clazz,clazz.getSimpleName());
	}
	@SuppressWarnings("rawtypes")
	static Object getByClass(Class clazz, String moduleName){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("from ${clazz} import ${clazz}".replace("${clazz}",moduleName));
		PyObject buildingClass = interpreter.get(moduleName);
		PyObject buildingObject = buildingClass.__call__();
		return buildingObject.__tojava__(clazz);
    }
	
	static Object createObject(Object interfaceType, String moduleName){
        Object javaInt = null;
        PyObject pyObject = null;
        // Create a PythonInterpreter object and import our Jython module
        // to obtain a reference.
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("from " + moduleName + " import " + moduleName);
        pyObject = interpreter.get(moduleName);

        try {
            // Create a new object reference of the Jython module and
            // store into PyObject.
            PyObject newObj = pyObject.__call__();
            // Call __tojava__ method on the new object along with the interface name
            // to create the java bytecode
            javaInt = newObj.__tojava__(Class.forName(interfaceType.toString().substring(
                        interfaceType.toString().indexOf(" ")+1,
                        interfaceType.toString().length())));
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        	logger.error("could find class :" + interfaceType, ex);
        }

        return javaInt;
    }
}
