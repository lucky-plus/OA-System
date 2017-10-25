/* 
* @Author: xgd
* @Date:   2017-10-22 22:33:01
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:33:01
*/

Ext.define('Admin.view.activiti.activitiProcessGridViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.activitiProcessGridViewModel',
    stores: {
        processLists: {
            type: 'activitiProcessStore',//Store reference ==Store的属性 alias: 'store.orderStore',       
            autoLoad: true //Auto load
        }
    }
});