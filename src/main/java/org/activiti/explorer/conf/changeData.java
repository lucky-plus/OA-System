package org.activiti.explorer.conf;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class changeData implements JavaDelegate{
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------------");  
        System.out.println("我需要改变数据");  
	}
}
