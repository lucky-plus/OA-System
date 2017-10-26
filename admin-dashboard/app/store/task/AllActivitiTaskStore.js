Ext.define('Admin.store.task.AllActivitiTaskStore', {
    extend: 'Ext.data.Store',
    alias: 'store.AllActivitiTaskStore',       //1.Store取别名（reference）
    model: 'Admin.model.activiti.AllActivitiTaskStoreModel',//2.设置model的全路径
  	proxy: {
		type: 'ajax',
		url: 'runtime/tasks',
		reader: {
			type:'json', 
			rootProperty: 'data',		//结果集名字的属性
			totalProperty: 'total'	//一共多少条记录的属性
		},
		simpleSortMode: true
	},

	pageSize: 15,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'createTime'
    }
});