Ext.define('Admin.model.address.AddressModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'userId'			,type: 'string'},
        {name:'realName' ,type: 'string'},
		{name:'deptName' ,type: 'string'},
		{name:'postName' ,type: 'string'},
        {name:'mobilePhone'	,type: 'string'},
		{name:'mail'	,type: 'string'},
		{name:'qq_number'	,type: 'int'}
    ]
});