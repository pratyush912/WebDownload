package com.pratz.task;

import com.pratz.exception.TaskException;
import com.pratz.task.model.Carrier;

public abstract class AbstractTask implements IsCOR {
	
	Carrier carrier;
	IsCOR nextCarrier;
	
	@Override
	public void execute(Carrier carrier) throws TaskException{
		if(carrier==null){
			throw new IllegalArgumentException("Carrier can not be null");
		}
		this.carrier = carrier;
		implement();
		
		if(nextCarrier!=null){
			nextCarrier.execute(carrier);
		}
	}
	
	@Override
	public void setNext(IsCOR carrier){
		this.nextCarrier = carrier;
	}

	abstract void implement() throws TaskException;

}
