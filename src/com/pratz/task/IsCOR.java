package com.pratz.task;

import com.pratz.exception.TaskException;
import com.pratz.task.model.Carrier;

public interface IsCOR {
	void execute(Carrier carrier) throws TaskException;
	void setNext(IsCOR next);

}
