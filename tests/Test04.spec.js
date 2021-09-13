import { Selector } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const checkBox  = Selector('a').withExactText('Checkboxradio');
    await t.click(checkBox);
})
test("Checkbox -1-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox1     = Selector('input[type=checkbox]').nth(0);
    const checkBox2     = Selector('input[type=checkbox]').nth(1);
    const checkBox3     = Selector('input[type=checkbox]').nth(2);
    const checkBox4     = Selector('input[type=checkbox]').nth(3);
    await t.expect(checkBox1.hasAttribute('disabled')).notOk('ready for testing');        
    await t.expect(checkBox2.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(checkBox3.hasAttribute('disabled')).notOk('ready for testing');
    await t.expect(checkBox4.hasAttribute('disabled')).notOk('ready for testing')
        const actual = Selector('label[for=checkbox-1]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 2 Star', 'check element text');
})
test("Checkbox -2-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
        const actual = Selector('label[for=checkbox-2]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 3 Star', 'check element text');
})
test("Checkbox -3-active", async t => {
    page.browserscroll();
    await t .switchToIframe(".demo-frame");
    const checkBox3     = Selector('input[type=checkbox]').nth(2);
        await t.expect(checkBox3.hasAttribute('disabled')).notOk('ready for testing');
        const actual = Selector('label[for=checkbox-3]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 4 Star', 'check element text', { timeout: 500 });
})
test("Checkbox -4-active", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame')
    const checkBox4     = Selector('input[type=checkbox]').nth(3);
    await t.expect(checkBox4.hasAttribute('disabled')).notOk('ready for testing')
        const actual = Selector('label[for=checkbox-4]');
        const txt = await actual.innerText;
        await t.expect(txt.toString()).eql(' 5 Star', 'check element text', { timeout: 500 });
})
test("Checkbox-1", async t => {
    page.browserscroll();
    await t .switchToIframe(".demo-frame");
    const label1   = Selector('label[for=checkbox-1]');
    await t.click(Selector('label').withAttribute('for', 'checkbox-1'));   
        await t.expect(label1.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-2", async t => {
    page.browserscroll();
    await t .switchToIframe(".demo-frame");
    const label2   = Selector('label[for=checkbox-2]');
    await t.click(Selector('label').withAttribute('for', 'checkbox-2'));   
        await t.expect(label2.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-3", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label3   = Selector('label[for=checkbox-3]');
    await t.click(Selector('label').withAttribute('for', 'checkbox-3'));   
        await t.expect(label3.classNames).contains('ui-checkboxradio-checked')
})
test("Checkbox-4", async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label4   = Selector('label[for=checkbox-4]');
    await t.click(Selector('label').withAttribute('for', 'checkbox-4'));   
    await t.expect(label4.classNames).contains('ui-checkboxradio-checked')
})
test('Checkbox -1,2 test', async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox1     = Selector('input[type=checkbox]').nth(0);
    const checkBox2     = Selector('input[type=checkbox]').nth(1);
    const checkBox3     = Selector('input[type=checkbox]').nth(2);
    const checkBox4     = Selector('input[type=checkbox]').nth(3);
    const ex1    = "rgb(0, 127, 255";
    const ex2    = "rgb(246, 246, 246)";
    await t.click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .expect(ex1).ok(checkBox1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox2.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(checkBox3.getStyleProperty('backgroundcolor')) 
        .expect(ex2).ok(checkBox4.getStyleProperty('backgroundcolor')); 
})
test("Checkbox -1,2,3",async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox1     = Selector('input[type=checkbox]').nth(0);
    const checkBox2     = Selector('input[type=checkbox]').nth(1);
    const checkBox3     = Selector('input[type=checkbox]').nth(2);
    const checkBox4     = Selector('input[type=checkbox]').nth(3);
    const ex1    = "rgb(0, 127, 255";
    const ex2    = "rgb(246, 246, 246)";
    await t.click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-3'))
        .expect(ex1).ok(checkBox1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox3.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(checkBox4.getStyleProperty('backgroundcolor')); 
})
test('Checkbox 1,2,3,4 test', async t =>{
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const checkBox1     = Selector('input[type=checkbox]').nth(0);
    const checkBox2     = Selector('input[type=checkbox]').nth(1);
    const checkBox3     = Selector('input[type=checkbox]').nth(2);
    const checkBox4     = Selector('input[type=checkbox]').nth(3);
    const ex1    = "rgb(0, 127, 255";
    const ex2    = "rgb(246, 246, 246)";
    await t
        .click(Selector('label').withAttribute('for', 'checkbox-1'))
        .click(Selector('label').withAttribute('for', 'checkbox-2'))
        .click(Selector('label').withAttribute('for', 'checkbox-3'))
        .click(Selector('label').withAttribute('for', 'checkbox-4'))
        .expect(ex1).ok(checkBox1.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox2.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox3.getStyleProperty('backgroundcolor'))
        .expect(ex1).ok(checkBox4.getStyleProperty('backgroundcolor'));  
})