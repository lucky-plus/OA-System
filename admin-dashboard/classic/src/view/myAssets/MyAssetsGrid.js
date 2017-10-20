Ext.define('Admin.view.myAssets.MyAssetsGrid', {    //1.修改文件路径
    extend: 'Ext.grid.Panel',         //2.继承的组件类型
		//3.重写继承组件的属性：
		id:'myAssetsGrid',
		xtype: 'myAssetsGrid',
		title:'<b>资产列表</b>',
		bind:'{myAssetsLists}',
    listeners:{
      cellclick:function(btn,td, cellIndex, record, tr, rowIndex){
        btn.up('panel').assets = rowIndex;
        btn.up('panel').assetsFind = record;
      }
    },
		selModel: Ext.create('Ext.selection.CheckboxModel'),
		columns: [
    {text: 'AssetsID',sortable:true ,dataIndex:'assetsId',hidden:true},
    {text: '资产编号' ,sortable:true ,dataIndex:'assetsNumber' ,width:100},
    {text: '资产名称' ,sortable:true ,dataIndex:'assetsName' ,width:150},
    {text: '创建时间'  ,sortable:true ,dataIndex:'assetsUsedTime'  ,width:170
    ,renderer: Ext.util.Format.dateRenderer('Y/m/d H:i:s')},
    {text: '资产类型',sortable:true ,dataIndex:'assetsType'    ,width:150},
    {text: '估计价值',sortable:true ,dataIndex:'assetsPrice' ,flex:1}
    ],
    bbar:Ext.create('Ext.PagingToolbar',{
      bind:'{assetsLists}',
      displayInfo:true,
      displayMsg:'第{0}-{1}条 共{2}条',
      emptyMsg:"没有任何记录",
    })
  });