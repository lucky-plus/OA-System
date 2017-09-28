Ext.define('Admin.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    //TODO: implement central Facebook OATH handling here

    onFaceBookLogin : function() {
        this.redirectTo('dashboard', true);
    },

    onLoginButton: function(btn){
        //(1) fs.form.findField(id/name).getValue();
        //(2) Ext.get(id/name).getValue();
        //(3) Ext.getCmp(id).getValue();
        //Ext.getCmp('login_userName').getValue();
        //btn.up("form").getForm().findField("password").getValue();
        var me = this;
        Ext.Ajax.request({
            url: 'loginAction',
            method: 'post',
            params: {
                userName: btn.up("form").getForm().findField("userName").getValue(),
                password: btn.up("form").getForm().findField("password").getValue()
            },
            success: function(response, options) {
                var json = Ext.util.JSON.decode(response.responseText);
                if(json.success){
                    me.redirectTo('dashboard', true);
                    window.location.reload();
                }else{
                    Ext.Msg.alert('登录失败', json.msg);
                }
            }
        });
    },
    onLoginAsButton: function() {
        this.redirectTo('login', true);
    },

    onNewAccount:  function() {
        this.redirectTo('register', true);
    },

    onSignupClick:  function() {
        this.redirectTo('dashboard', true);
    },

    onResetClick:  function() {
        this.redirectTo('dashboard', true);
    }
});