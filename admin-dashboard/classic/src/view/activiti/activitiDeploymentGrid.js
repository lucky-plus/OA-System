/* 
* @Author: xgd
* @Date:   2017-10-22 21:46:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:24:30
*/

Ext.define('Admin.view.activiti.activitiDeploymentGrid', {        //1.修改文件路径
      extend: 'Ext.grid.Panel',                 //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiDeploymentGrid',
    title:'<b>流程部署</b>',

    selModel: Ext.create('Ext.selection.CheckboxModel'),
    
    columns: [
        {dataIndex:'id'          ,text:'部署Id',sortable:true , type: 'string', hidden:true},
        {dataIndex:'name'        ,text:'名字',sortable:true , type: 'string', width:130},
        {dataIndex:'key'         ,text:'关键字',sortable:true , type: 'string', width:120},
		{dataIndex:'deploymentTime',text:'部署时间', sortable: true, type:'string',renderer:new Ext.util.Format.dateRenderer('Y-m-d'), width:200},
        {dataIndex:'description' ,text:'描述',sortable:true , type: 'string', flex:1}
    ],  

	bind: '{deploymentLists}',

    tbar: Ext.create('Ext.Toolbar', {
            items:[ {
            text: '新增部署',
            iconCls:'x-fa fa-plus',
            ui:'soft-blue',
            listeners:{
                click:'activitiDeploymentAdd'
            }
        },'-', {
            text: '删除部署',
            iconCls:'x-fa fa-trash',
            handler: 'activitiDeploymentDelete'
        }]
    }),
    
    
    
    bbar: Ext.create('Ext.PagingToolbar', {
        bind:'{deploymentLists}',
        displayInfo: true,
        displayMsg: '第 {0} - {1}条， 共 {2}条',
        emptyMsg: "暂无数据",
    }),
    
});