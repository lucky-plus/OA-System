Ext.define('Admin.model.department.DepartmentModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'deptId'			,type: 'int'},
		{name:'deptName' ,			type: 'string'},
		{name:'parentId'			,type: 'int'}
    ]
});
