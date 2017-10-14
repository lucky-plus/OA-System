Ext.define('Admin.store.department.DepartmentStore', {
    extend: 'Ext.data.Store',
    alias: 'store.departmentStore',			  //1.Store取别名（reference）
	model: 'Admin.model.department.DepartmentModel',
	proxy: {
		type: 'ajax',
		url: 'dept/findAll.json',
		reader: {
			type:'json', 
			rootProperty: 'content',		//结果集名字的属性
			totalProperty: 'totalElements'	
		},
		simpleSortMode: true
	},

	pageSize: 25,
	autoLoad: true,
	remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'deptId'
    }
});