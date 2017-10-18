Ext.define('Admin.store.department.DepartmentStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.departmentStore',			  //1.Store取别名（reference）
	id:'departmentStore',
	proxy: {
		type: 'ajax',
		url: 'dept/findNodes',
		reader: {
			type:'json', 
		}
	},

	root:{
		text : '组织架构',
		expanded : true	 //发送node=root
	}

});