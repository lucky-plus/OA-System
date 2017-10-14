/**
	1.绑定到主视图
	2.通过bind属性绑定到具体的子视图
8*/
Ext.define('Admin.view.task.TaskViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.taskViewModel',
	stores: {
        taskLists: {
            type: 'taskStore',//Store reference ==Store的属性 alias: 'store.orderStore',		
            autoLoad: true //Auto load
        }
    }
});
