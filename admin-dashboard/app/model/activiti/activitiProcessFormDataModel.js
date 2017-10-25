/* 
* @Author: xgd
* @Date:   2017-10-22 23:29:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:30:24
*/

Ext.define('Admin.model.activiti.activitiProcessFormDataModel', {
    extend: 'Admin.model.Base',
    fields: [
        {name:'id'            ,type: 'string'},
        {name:'version'          ,type: 'int'},
		{name:'suspended'     ,type: 'boolean'},
        {name:'key'           ,type: 'string'},
		{name:'name'  ,type: 'string'},
        {name:'description'   ,type: 'string'}
    ]
});
