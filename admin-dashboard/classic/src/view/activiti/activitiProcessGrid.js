/* 
* @Author: xgd
* @Date:   2017-10-22 21:46:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:31:22
*/

Ext.define('Admin.view.activiti.activitiProcessGrid', {        //1.修改文件路径
      extend: 'Ext.grid.Panel',                 //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiProcessGrid',
    title:'<b>流程定义</b>',
	id: 'processGrid',
	
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    
    columns: [
        {dataIndex:'id'            ,text:'流程定义Id',sortable:true , type: 'string', hidden:true},
        {dataIndex:'name'          ,text:'名字',sortable:true , type: 'string', width:130},
		{dataIndex:'suspended'     ,text:'是否暂停',sortable:true , type: 'boolean', width:80},
		{dataIndex:'key'           ,text:'关键字',sortable:true , type: 'string', width:180},
		{dataIndex:'deploymentTime',text:'开启时间',sortable:true, type: 'date',renderer:new Ext.util.Format.dateRenderer('Y-m-d'), width:200},
        {dataIndex:'description'   ,text:'描述',sortable:true , type: 'string', flex:1},
		{xtype: 'actioncolumn'     ,text:'操作' ,width:100,tdCls: 'action',  
			items: ['-',{
					icon:'resources/images/icons/start-processInstance.png',
					tooltip: '开始',
					handler: ('startProcessInstance')
				},'-', {
					icon:'resources/images/icons/激活.png',
					tooltip: '激活',
					handler: ('activateProcess') 
				},'-', {
					icon:'resources/images/icons/详情.png',
					tooltip: '详情',
					handler: ('showDetails') 
				}
			],
			width:180
		}
    ],  
	
    bind: '{processLists}',       
    
    bbar: Ext.create('Ext.PagingToolbar', {
        bind:'{processLists}',
        displayInfo: true,
        displayMsg: '第 {0} - {1}条， 共 {2}条',
        emptyMsg: "暂无数据",
    }),
    
});