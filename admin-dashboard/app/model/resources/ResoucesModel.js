Ext.define('Admin.model.resources.ResourcesModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'resourcesId'			,type: 'int'},
        {name:'resourcesName' ,type: 'string'},
        {name:'resourcesTime'	,type: 'date'}
    ]
});