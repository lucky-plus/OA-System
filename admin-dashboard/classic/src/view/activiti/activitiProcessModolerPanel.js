/* 
* @Author: xgd
* @Date:   2017-10-22 21:46:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:31:22
*/

Ext.define('Admin.view.activiti.activitiProcessModolerPanel', {        //1.修改文件路径
      extend: 'Ext.grid.Panel',                 //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiProcessModolerPanel',
    title:'<b>流程模型</b>',
	
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    
    columns: [
		{dataIndex:'id'			      ,text:'id' ,sortable:true ,width:180 , type: 'string',hidden:true},
        {dataIndex:'name'             ,text:'模型名字',sortable:true ,flex:1, type: 'string'},
        {dataIndex:'key'              ,text:'关键字',sortable:true , type: 'string', width:130,hidden:true},
		{dataIndex:'category'         ,text:'分类',sortable:true , type: 'string', width:80,hidden:true},
		{dataIndex:'version'          ,text:'版本',sortable:true , type: 'int', width:180},
		{dataIndex:'metaInfo'         ,text:'元信息',sortable:true , type: 'string', width:180,hidden:true},
        {dataIndex:'deploymentId'     ,text:'部署Id',sortable:true , type: 'string', width:180,hidden:true},
		{dataIndex:'createTime'       ,text:'创建时间',sortable:true, type: 'date',renderer:new Ext.util.Format.dateRenderer('Y-m-d'), width:200},
		{dataIndex:'lastUpdateTime'   ,text:'上次更新时间',sortable:true, type: 'date',renderer:new Ext.util.Format.dateRenderer('Y-m-d'), width:200},
		{xtype: 'actioncolumn'     ,text:'操作' ,width:100,tdCls: 'action',  
			items: ['-',{
					icon:'resources/images/icons/start-processInstance.png',
					tooltip: '继续编辑',
					handler: ('showModelerEditor')
				}
			],
			width:180
		}
    ],  
	
    bind: '{modelLists}',       
    
    bbar: Ext.create('Ext.PagingToolbar', {
        bind:'{modelLists}',
        displayInfo: true,
        displayMsg: '第 {0} - {1}条， 共 {2}条',
        emptyMsg: "暂无数据",
    }),
    
});