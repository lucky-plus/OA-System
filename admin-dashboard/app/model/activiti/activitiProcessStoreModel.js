/* 
* @Author: xgd
* @Date:   2017-10-22 23:29:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:24
*/

Ext.define('Admin.model.activiti.activitiProcessStoreModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'id'            ,type: 'string'},
        {name:'name'          ,type: 'string'},
		{name:'suspended'     ,type: 'boolean'},
        {name:'key'           ,type: 'string'},
		{name:'deploymentTime',type: 'date'},
        {name:'description'   ,type: 'string'}
    ]
});
