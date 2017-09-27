Ext.define('Admin.model.role.RoleModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'roleId' ,type: 'int'},
        {name:'roleName' ,type: 'string'},
        {name:'roleLevel'	,type: 'int'},
		{name:'modulesText'		,type: 'string'}
    ]
});
