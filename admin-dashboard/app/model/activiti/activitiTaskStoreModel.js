/* 
* @Author: xgd
* @Date:   2017-10-22 23:29:37
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:23
*/

Ext.define('Admin.model.activiti.activitiTasksStoreModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'id'              ,type: 'string'},
        {name:'name'            ,type: 'string'},
		{name:'suspended'       ,type: 'boolean'},
        {name:'delegationState' ,type: 'string'},
		{name:'createTime'      ,type: 'date'},
        {name:'description'     ,type: 'string'}

    ]
});
