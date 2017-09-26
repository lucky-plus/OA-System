Ext.define('Admin.model.address.AddressModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'addressId'			,type: 'int'},
        {name:'addressName' ,type: 'string'},
		{name:'addressDepartment' ,type: 'string'},
        {name:'addressPhone'	,type: 'int'},
		{name:'addressEmail'	,type: 'string'},
		{name:'addressQQ'	,type: 'int'}
    ]
});