Ext.define('Admin.store.staff.StaffStore', {
    extend: 'Ext.data.Store',
    alias: 'store.staffStore',			  //1.Store取别名（reference）
	model: 'Admin.model.staff.StaffModel',
	proxy: {
		type: 'ajax',
		url: 'staff/findPage.json',
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
        property: 'userId'
    }
});