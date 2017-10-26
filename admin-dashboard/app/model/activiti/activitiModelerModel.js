/* 
* @Author: xgd
* @Date:   2017-10-22 23:28:46
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:25
*/

Ext.define('Admin.model.activiti.activitiModelerModel', {
    extend: 'Admin.model.Base',
    fields: [
		{name:'id', 			type:'string'},
        {name:'name'            ,type: 'string'},
        {name:'key'   ,type: 'string'},
		{name:'category', type:'string'},		
		{name:'version', type:'int'},
		{name:'metaInfo'     ,type: 'string'},
        {name:'deploymentId'   ,type: 'string'},
		{name:'createTime'   ,type: 'date'},
		{name:'lastUpdateTime', type:'date'}
    ]
});
