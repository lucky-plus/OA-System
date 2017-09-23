Ext.define('Admin.model.order.OrderModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'id'			,type: 'int'},
        {name:'orderNumber' ,type: 'string'},
        {name:'createTime'	,type: 'date'},
		{name:'level'		,type: 'string'},
		{name:'price'		,type: 'float'}
    ]
});
