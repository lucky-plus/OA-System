Ext.define('Admin.model.authority.AuthorityModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'userId' ,type: 'string'},
		{name:'roleId' ,type: 'int'},
        {name:'userName' ,type: 'string'},
        {name:'realName' ,type: 'string'},
        {name:'roleName' ,type: 'string'},
		{name:'modulesText'		,type: 'string'}
    ]
});
