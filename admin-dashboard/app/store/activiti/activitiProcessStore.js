/* 
* @Author: xgd
* @Date:   2017-10-22 22:58:04
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:15:56
*/

Ext.define('Admin.store.activiti.activitiProcessStore', {
    extend: 'Ext.data.Store',
    alias: 'store.activitiProcessStore',       //1.Store取别名（reference）
    model: 'Admin.model.activiti.activitiProcessStoreModel',//2.设置model的全路径
    proxy: {
        type: 'ajax',
        url: 'repository/process-definitions',
        reader: {
            type:'json', 
            rootProperty: 'data',        //结果集名字的属性
            totalProperty: 'total'  //一共多少条记录的属性
        },
		extraParams:{
			size : 15
		},
        simpleSortMode: true
    },

    pageSize: 15,
    autoLoad: true,
    remoteSort: true,//全局排序
    sorters: {
        direction: 'DESC',
        property: 'id'
    }
});
