Ext.define('Admin.model.assets.MyAssetsModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'assetsId'    ,type: 'int'},
        {name:'assetsNumber'    ,type: 'string'},
        {name:'assetsUsedTime'  ,type: 'date'},
        {name:'beginDate'   ,type: 'date'},
        {name:'endDate'     ,type: 'date'},
        {name:'assetsName'    ,type: 'string'},
        {name:'assetsPrice'   ,type: 'float'},
        {name:'highPrice'   ,type: 'float'},
        {name:'lowPrice'    ,type: 'float'},
        {name:'assetsType'    ,type: 'string'},
        {name:'userId'    ,type: 'string'},
        {name:'realName'    ,type: 'string'}
    ]
});