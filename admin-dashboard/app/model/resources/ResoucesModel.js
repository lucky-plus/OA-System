Ext.define('Admin.model.resources.ResourcesModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'resId'			,type: 'int'},
        {name:'resName' ,type: 'string'},
        {name:'resTime'	,type: 'date'},
		{name:'resIdentify'	,type: 'string'},
		{name:'userId',type: 'string'}
    ]
});