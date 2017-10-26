/* 
* @Author: xgd
* @Date:   2017-10-22 23:33:50
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:35:16
*/

Ext.define('Admin.view.activiti.activitiProcessInstanceGrid', {        //1.修改文件路径
      extend: 'Ext.grid.Panel',                 //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiProcessInstanceGrid',
    title:'<b>流程实例</b>',

    selModel: Ext.create('Ext.selection.CheckboxModel'),

    columns: [
        {dataIndex:'id'   					 ,text:'实例Id'	  ,sortable:true , type: 'string', hidden:true},
        {dataIndex:'processDefinitionKey'    ,text:'流程定义Key'   ,sortable:true , type: 'string', flex:1},
		{dataIndex:'name'					 ,text:'名字'       ,sortable:true , type: 'string' ,  width:200},
		{dataIndex:'businessKey'             ,text:'业务关键字' ,sortable:true , type: 'string' ,  width:200},
        {dataIndex:'suspended'      		 ,text:'是否暂定'   ,sortable:true , type: 'boolean', width:80},
        {dataIndex:'completed'    			 ,text:'是否完成'   ,sortable:true , type: 'string' ,  width:80},
		{xtype: 'actioncolumn'     ,text:'操作' ,width:100,tdCls: 'action',  
			items: ['-',{
					icon:'resources/images/icons/start-processInstance.png',
					tooltip: '查看进度',
					handler: ('searchWhereToBe')
				},'-', {
					icon:'resources/images/icons/激活.png',
					tooltip: '暂停',
					handler: ('searchWhereToBe') 
				},'-', {
					icon:'resources/images/icons/详情.png',
					tooltip: '详情',
					handler: ('searchWhereToBe') 
				}
			],
			width:180
		}
    ],  

	bind:'{processInstanceLists}',
    // tbar: Ext.create('Ext.Toolbar', {
            // items:[ {
            // text: '新增部署',
            // id: 'roleAddButton',
            // iconCls:'x-fa fa-plus',
            // ui:'soft-blue',
            // listeners:{
                // click:'activitiDeploymentAdd'
            // }
        // },'-', {
            // text: '删除部署',
            // id: 'roleDeleteButton',
            // iconCls:'x-fa fa-trash',
            // handler: 'activitiDeploymentDelete'
        // }]
    // }),
    
    bbar: Ext.create('Ext.PagingToolbar', {
        bind:'{processInstanceLists}',
        displayInfo: true,
        displayMsg: '第 {0} - {1}条， 共 {2}条',
        emptyMsg: "暂无数据",
    }),
    
});