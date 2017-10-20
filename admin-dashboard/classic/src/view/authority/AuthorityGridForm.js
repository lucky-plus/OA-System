Ext.define('Admin.view.authority.AuthorityGridForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.authorityGridForm',
    id:'authorityGridForm',//Ext.getCmp('authorityGridForm');
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor',
        'Ext.form.field.TextArea',
        'Ext.form.field.Time',
        'Ext.form.field.ComboBox',
        'Ext.form.field.Date',
        'Ext.form.field.Radio',
        'Ext.form.field.Hidden'
    ],
    //viewModel: {type: 'emailcompose'},
    //cls: 'email-compose',
    controller: 'AuthorityViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 60,
        labelSeparator: ''
    },
    items: [{
        xtype: 'hidden',
        fieldLabel: 'userId',
        //allowBlank: false,
        name:'userId'
    },{
        xtype: 'textfield',
        fieldLabel: '用户名称',
        name:'userName',
        readOnly:true
    },
    {
        xtype: 'radiogroup',
        id: 'radiogroupOperation',
        fieldLabel: '角色名称',
        columns: 5,
        vertical: true,
        // items: [
        //  { boxLabel: 'r1',   name: 'roleId', inputValue: '1' },
        //  { boxLabel: 'r2', name: 'roleId', inputValue: '2' },
        //  { boxLabel: 'r3', name: 'roleId', inputValue: '3' },
        //  { boxLabel: 'r4',   name: 'roleId', inputValue: '4' }
        // ]
        listeners: {
          render: function () {
            //通过extjs的ajax获取
            Ext.Ajax.request({
                url: 'role/findRoleByLevel.json?roleLevel='+loginUserRoleLevel,
                // 这里async 必须设置成false 否则页面加载时，无法将动态创建的checkBoxGroup添加到容器中
                async : false,
                success: function (response) {
                    var data = eval("(" + response.responseText + ")");
                    var len = data.length;//obj.data.length; "Table"这里的Table指的是后台返回 类似于data
                    if (data == null || len == 0) {
                        return;
                    }

                    var radiogroup = Ext.getCmp("radiogroupOperation");
                    for (var i = 0; i < len; i++) {
                        var radiobox = new Ext.form.Radio(
                          {
                              boxLabel: data[i].roleName,
                              name: 'roleId',
                              inputValue: data[i].roleId,
                              checked: false
                          });
                        radiogroup.items.add(radiobox);
                    }
                    //重新调整版面布局
                    // var form = Ext.getCmp('authorityGridForm');
                    // form.reload();
                }
            });
          }
        }
    }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
            xtype: 'button',
            //ui: 'soft-red',
            text: '提交',
            handler: 'authorityGridFormSubmit'
        },{
            xtype: 'button',
            //ui: 'gray',
            text: '取消',
            handler: 'authorityGridWindowClose'
        }]
    }
});