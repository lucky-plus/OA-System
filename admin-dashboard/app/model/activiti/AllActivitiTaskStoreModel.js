/* 
* @Author: xgd
* @Date:   2017-10-22 23:29:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:24
*/

Ext.define('Admin.model.activiti.AllActivitiTaskStoreModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'assignee'            ,type: 'string'},
        {name:'createTime'          ,type: 'date'},
		{name:'delegationState'     ,type: 'string'},
        {name:'description'           ,type: 'string'},
		{name:'dueDate',type: 'date'},
        {name:'id'   ,type: 'string'},
		{name:'name'   ,type: 'string'},
		{name:'suspended'   ,type: 'boolean'},
		{name:'taskDefinitionKey'   ,type: 'string'},
		{name:'owner', type: 'string'},
		
    ]
});
