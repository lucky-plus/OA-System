/* 
* @Author: xgd
* @Date:   2017-10-22 23:28:46
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:25
*/

Ext.define('Admin.model.activiti.activitiProcessInstanceStoreModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'id'            ,type: 'string'},
        {name:'processDefinitionKey'   ,type: 'string'},
		{name:'name', type:'string'},		
		{name:'businessKey', type:'string'},
		{name:'suspended'     ,type: 'boolean'},
        {name:'completed'   ,type: 'boolean'}
    ]
});
