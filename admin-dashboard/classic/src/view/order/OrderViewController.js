Ext.define('Admin.view.order.OrderViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.orderViewController',

    orderGridOpenAddWindow: function(bt) {
        // alert("Add Wiondws");
        /* 第一种方式
        var cfg = Ext.apply({
          xtype: 'orderGridWindow'
          ,items: [Ext.apply({xtype: 'orderGridForm'})]
        },{
          title:'创建订单'//,width: 800//,height: 600
        });
        Ext.create(cfg);
        */
       //第二种方式
       Ext.widget('orderGridWindow',{
            title:'创建订单'
            ,items: [{xtype: 'orderGridForm'}]
        });
    },
    
    orderGridOpenEditWindow: function(btn) {
        var grid = btn.up('gridpanel');//获取Grid视图
        var selModel = grid.getSelectionModel();//获取Grid的SelectionModel
        if (selModel.hasSelection()) {//判断是否选中记录
           var record = selModel.getSelection()[0];//获取选中的第一条记录
           //创建修改window和form
           var orderGridWindow = Ext.widget('orderGridWindow',{
                title:'修改订单',
                items: [{xtype: 'orderGridForm'}]
            });
           //让form加载选中记录
           orderGridWindow.down("form").getForm().loadRecord(record);
        }else{
            Ext.Msg.alert('提示',"请选择一行数据进行编辑!");
        }
    },
    
    orderGridDeleteDate: function(btn) {
        var grid = btn.up('gridpanel');
        var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.id);
                    })
                    Ext.Ajax.request({ 
                        url : 'order/delete', 
                        method : 'post', 
                        params : { 
                            ids:selectIds
                        }, 
                        success: function(response, options) {
                            var json = Ext.util.JSON.decode(response.responseText);
                            if(json.success){
                                Ext.Msg.alert('操作成功', json.msg);
                                grid.getStore().reload();
                            }else{
                                Ext.Msg.alert('操作失败', json.msg);
                            }
                        }
                    });

                }
            });
        }
    },

    orderGridFormSubmit: function(btn) {
        var orderGridForm = btn.up('form').getForm();
        var win = btn.up('window');
        orderGridForm.submit( { 
            //waitTitle : '请稍后...', 
            //waitMsg : '正在保存订单信息,请稍后...', 
            url : 'order/saveOrUpdate', 
            method : 'post', 
            success : function(form, action) { 
                Ext.Msg.alert("提示",action.result.msg); 
                win.close();
                Ext.getCmp('orderGrid').store.reload();
            }, 
            failure : function(form, action) { 
                Ext.Msg.alert("提示",action.result.msg); 
                
            } 
        }); 
    },
    
    orderGridWindowClose: function(btn) {
        var win = btn.up('window');
        if(win){
            win.close();
        }
    }

});
