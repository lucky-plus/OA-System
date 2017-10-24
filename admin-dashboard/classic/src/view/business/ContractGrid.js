Ext.define('Admin.view.business.ContractGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'contractGrid',
	id:'contractGrid',
    bind:'{contractLists}',
	title:'<b>合同管理</b>',
    controller: 'contractViewController',
    // viewModel: {
        // type: 'contractList'
    // },
	requires: [
		'Ext.window.Toast'
    ],
	selModel: Ext.create('Ext.selection.CheckboxModel'),
    listeners:{
        cellclick : function(grid,td, cellIndex, record, tr, rowIndex, e, eOpts ){
			if(cellIndex>0&&cellIndex<8){
            var cfg = Ext.apply({
                xtype: 'contractWindow',
				controller: 'contractViewController',
                items: [
                    Ext.create('Ext.panel.Panel', {
                        rrdata: record,
                        scrollable: true,
                        // layout: {
                        //     type: 'vbox',       // Arrange child items vertically
                        //     align: 'stretch',    // Each takes up full width
                        //     padding: 0
                        // },
                        items: [{
                            xtype:'panel',  //或者xtype: 'component',
                            height : 620,
                            scrollable: true,
                            picSize : 100,
                            html:'<img width="100%" src="'+grid.getStore().getAt(rowIndex).data.pictureFileName+'">'    //指定url路径  
                        },{
                            xtype: 'toolbar',
                            width : 1200,
                            items: [
                                {
                                    // xtype: 'button', // default for Toolbars
                                    text: '放大 +',
                                    handler: function(btn){
                                        var tmpPic = btn.up('panel').items.getAt(0);
                                        if(tmpPic.picSize){
                                            if(tmpPic.picSize>=50){
                                                btn.up('panel').items.getAt(1).items.getAt(1).setDisabled(false);
                                            }
                                            tmpPic.picSize += 5;
                                            var newHtml = "<img width=\""+tmpPic.picSize+"%\" src=\""+grid.getStore().getAt(rowIndex).data.pictureFileName+"\"/>";
                                            // Ext.Msg.alert('Info','ヽ(●-`Д´-)ノ'+tmpPic.picSize);
                                            tmpPic.setHtml(newHtml);
                                        }
                                    }
                                },{
                                    // xtype: 'button', // default for Toolbars
                                    text: '缩小 -',
                                    handler: function(btn){
                                        var tmpPic = btn.up('panel').items.getAt(0);
                                        if(tmpPic.picSize&&tmpPic.picSize>50){
                                            tmpPic.picSize -= 5;
                                            var newHtml = "<img width=\""+tmpPic.picSize+"%\" src=\""+grid.getStore().getAt(rowIndex).data.pictureFileName+"\"/>";
                                            // Ext.Msg.alert('Info','ヽ(●-`Д´-)ノ'+tmpPic.picSize);
                                            tmpPic.setHtml(newHtml);
                                        } else{
                                            Ext.toast({
                                                html: '图片已经缩小到最小比例50%<br>缩小功能现已经禁用',
                                                width: 200,
                                                align: 'b'
                                            });
                                            btn.setDisabled(true);
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                    })
                ]
            }, {
                // Any configs that you would like to apply for window popup goes here
                title: 'Compose Message'
            });
    
            Ext.create(cfg);            
            // Ext.Msg.alert('提示',record.get('title'));
        }
		}
    },
    columns:[
        {text: '合同ID',sortable:true ,dataIndex:'contractId',hidden:true},
		{text: '合同编号',sortable:true ,dataIndex:'contractNum',width:200},
        {text: '合同名称',sortable:true ,dataIndex:'contractName',width:300},
		{text: '合作单位',sortable:true ,dataIndex:'company',width:300},
		{text: '日期',sortable:true ,dataIndex:'contractDate',width:100,
			renderer: Ext.util.Format.dateRenderer('Y/m/d')},
		{text: '发布者',sortable:true ,dataIndex:'userName',width:100},
		{xtype: 'actioncolumn',  text: '操作' ,flex:1,tdCls: 'action',  
		items: ['-',{
					icon:'resources/images/icons/dowanload.png',
					tooltip: '下载',
					handler:'contractGridDownloadOne'
				},'-', 
				{
					icon:'resources/images/icons/delete.png',
					tooltip: '删除',
					handler: 'contractGridDeleteOne'
          }]  }
    ],
	
	dockedItems:[
		Ext.create('Ext.Toolbar', {
			id:'contractBar',
			items:[
			{		
				xtype:'tbtext',
				text:'合作单位：'
			},{
				xtype:'textfield',
				width:300
			},{
				xtype:'tbtext',
				text:'时间：'
			},{
				xtype:'datefield',
				format:'Y-m-d',  
				value:'1972-01-01',
				editable:false
			},{
				xtype:'tbtext',
				text:'至：'
			},{
				xtype:'datefield',
				format:'Y-m-d',  
				value:new Date(),
				editable:false,
				listeners: {  
					focus: function(){
						var cc = Ext.getCmp('contractBar').items.getAt(9).getValue();
						this.setMinValue(cc);
					}  	
				},
			},{
				text:'查找',
				handler:'contractGridFind'
			}]}),
		Ext.create('Ext.Toolbar',{
			items:[ 
			{
				text: '上传',
				iconCls:'x-fa fa-plus',
				ui:'soft-blue',
				handler:function(){
						var cfg = Ext.apply({
							xtype:'contractEditWindow'
						},
						{
							title:'合同上传',
							items:[Ext.apply({xtype:'contractForm'})]
						});
						Ext.create(cfg);
				}
			},'-', 
			{
				text: '下载',
				iconCls:'x-fa fa-arrow-circle-o-down',
				handler: 'contractGridDownloadMany'
			},'-', 
			{
				text: '批量删除',
				iconCls:'x-fa fa-trash',
				handler: 'contractGridDelete'
			}]
		})
	],
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{contractLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "No topics to display",
	}),
  
    

});