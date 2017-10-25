/* 
* @Author: xgd
* @Date:   2017-10-22 22:00:27
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:22:36
*/

Ext.define('Admin.view.activiti.activitiDeploymentGridViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.activitiDeploymentGridViewModel',
    stores: {
        deploymentLists: {
            type: 'activitiDeploymentStore',//Store reference ==Store的属性 alias: 'store.orderStore',       
            autoLoad: true //Auto load
        }
    }
});