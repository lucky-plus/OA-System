/* 
* @Author: xgd
* @Date:   2017-10-22 21:52:07
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 21:56:10
*/

Ext.define('Admin.model.activiti.activitiDeploymentStoreModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'id' ,type: 'string'},
        {name:'name' ,type: 'string'},
        {name:'key', type: 'string'},
		{name:'deploymentTime', type: 'date'},
        {name:'description'   ,type: 'string'}
    ]
});
