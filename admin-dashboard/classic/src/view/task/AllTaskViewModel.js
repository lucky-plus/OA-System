/**
	1.绑定到主视图
	2.通过bind属性绑定到具体的子视图
8*/
Ext.define('Admin.view.task.AllTaskViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.allTaskViewModel',
	stores: {
        taskLists: {
            type: 'allTaskStore',//Store reference ==Store的属性 alias: 'store.orderStore',		
            autoLoad: true //Auto load
        },
		activitiTaskLists: {
			type: 'AllActivitiTaskStore',
			autoLoad: true
		}
    }
});
