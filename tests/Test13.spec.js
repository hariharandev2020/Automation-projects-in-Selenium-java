import { Selector,ClientFunction  } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`
.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const spinner  = Selector('a').withExactText('Spinner');
    await t.click(spinner);
})
test('button visiblity check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const input    = Selector('#spinner');
    const visib    = Selector('#disable');
    const toggle   = Selector('#destroy');
    const getval   = Selector('#getvalue');
    const setval   = Selector('#setvalue')
    await t.expect(await input.getAttribute('class')).notContains('disabled');
    await t.expect(await visib.getAttribute('class')).notContains('disabled');
    await t.expect(await toggle.getAttribute('class')).notContains('disabled');
    await t.expect(await getval.getAttribute('class')).notContains('disabled');
    await t.expect(await setval.getAttribute('class')).notContains('disabled');
})
test('label and input name test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const label      = Selector('label[for=spinner]');
    const visib    = Selector('#disable');
    const toggle   = Selector('#destroy');
    const getval   = Selector('#getvalue');
    const setval   = Selector('#setvalue')
    await t.expect(await label.innerText).eql('Select a value:');
    await t.expect(await visib.innerText).eql('Toggle disable/enable');
    await t.expect(await toggle.innerText).eql('Toggle widget');
    await t.expect(await getval.innerText).eql('Get value');
    await t.expect(await setval.innerText).eql('Set value to 5');
});
test('Input buttons check', async t => {
    page.browserscroll();
    const up = Selector('body > p:nth-child(1) > span > a.ui-button.ui-widget.ui-spinner-button.ui-spinner-up.ui-corner-tr.ui-button-icon-only');
    const input    = Selector('#spinner');
    const down = Selector('body > p:nth-child(1) > span > a.ui-button.ui-widget.ui-spinner-button.ui-spinner-down.ui-corner-br.ui-button-icon-only');
    await t.switchToIframe('.demo-frame');
    await t.click(up);
    await t.expect(await input.value).eql('1');
    await t.click(down);
    await t.expect(await input.value).eql('0');
})
test('Input Enabled and disable button check', async t => {
        page.browserscroll();
        await t.switchToIframe('.demo-frame');
        const input    = Selector('#spinner');
        const visib    = Selector('#disable');
        await t.click(visib);
        await t.expect(await input.hasAttribute('disabled')).ok();
        await t.click(visib);
        await t.expect(await input.hasAttribute('disabled')).notOk();
})
    test('Toggle widget check', async t => {
        page.browserscroll();
        await t.switchToIframe('.demo-frame');
        const input    = Selector('#spinner');
        const toggle   = Selector('#destroy');        const getval   = Selector('#getvalue');
        await t.click(toggle);
        const heightval2 = await input.getStyleProperty('height');
        const widthval2 = await input.getStyleProperty('width');
        await t.expect(heightval2).eql('15px');
        await t.expect(widthval2).eql('150px');
        await t.click(toggle);
        const widthval = await input.getStyleProperty('width');
        const heightval = await input.getStyleProperty('height');
        await t.expect(heightval).eql('18px');
        await t.expect(widthval).eql('176px');
    })

test('Togle Enabled and disable button check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const input    = Selector('#spinner');
    const toggle   = Selector('#destroy');
    await t.click(toggle);
    await t.expect(await input.hasAttribute('disabled')).notOk();
})
    test.skip('Get value alert check', async t =>{
        page.browserscroll();
        await t.switchToIframe('.demo-frame')
        .setNativeDialogHandler((type, text, url) => {
            switch (type) {
                case 'confirm':
                    switch (text) {
                        case 'Do you want to subscribe?':
                            return false;
                        case 'Do you want to delete your account?':
                            return true;
                        default:
                            throw 'Unexpected confirm dialog!';
                    }
                case 'prompt':
                    return 'Hi there';
                case 'alert':
                    t
                    .pressKey('enter')
                    throw 'An alert was invoked!';
            }
        })
        .click(getval)
        .pressKey('enter')
    })
    test('Set value', async t =>{
        page.browserscroll();
        const input    = Selector('#spinner');
        const setval   = Selector('#setvalue');
        await t.switchToIframe('.demo-frame')
        .click(setval);
        await t.expect(input.value).eql('5');
 })