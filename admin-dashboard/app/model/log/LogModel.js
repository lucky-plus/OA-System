Ext.define('Admin.model.log.LogModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'createDate' ,type: 'date'},
        {name:'operation' ,type: 'string'},
        {name:'userName' ,type: 'string'},
        {name:'realName' ,type: 'string'},
		{name:'content'	,type: 'string'}
    ]
});
