Ext.define('Admin.model.authority.AuthorityModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'userId' ,type: 'int'},
		{name:'roleId' ,type: 'int'},
        {name:'userName' ,type: 'string'},
        {name:'roleName' ,type: 'string'},
		{name:'modulesText'		,type: 'string'}
    ]
});
