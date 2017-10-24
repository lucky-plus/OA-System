Ext.define('Admin.model.business.ContractModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'contractId',type: 'int'},
        {name:'contractNum'	,type: 'string'},
        {name:'contractName' ,type: 'string'},
		{name:'Company',type: 'string'},
		{name:'contractFile',type: 'string'},
		{name:'contractDate',type: 'date'},
		{name:'contractState',type: 'string'},
		{name:'userId',type: 'string'}
    ]
});
