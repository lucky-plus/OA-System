/* 
* @Author: xgd
* @Date:   2017-10-22 22:33:01
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:33:01
*/

Ext.define('Admin.view.activiti.activitiProcessInstanceGridViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.activitiProcessInstanceGridViewModel',
    stores: {
        processInstanceLists: {
            type: 'activitiProcessInstanceStore',//Store reference ==Storeµƒ Ù–‘ alias: 'store.orderStore',       
            autoLoad: true //Auto load
        }
    }
});