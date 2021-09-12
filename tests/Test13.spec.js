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

const lbl      = Selector('label[for=spinner]');
const input    = Selector('#spinner');
const visib    = Selector('#disable');
const toggle   = Selector('#destroy');
const getval   = Selector('#getvalue');
const setval   = Selector('#setvalue');
const up = Selector('body > p:nth-child(1) > span > a:nth-child(1)');
const down = Selector('body > p:nth-child(1) > span > a:nth-child(2)');

test('button visiblity check', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await input.getAttribute('class')).notContains('disabled');
    await t.expect(await visib.getAttribute('class')).notContains('disabled');
    await t.expect(await toggle.getAttribute('class')).notContains('disabled');
    await t.expect(await getval.getAttribute('class')).notContains('disabled');
    await t.expect(await setval.getAttribute('class')).notContains('disabled');
})
test('label and input name test', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    await t.expect(await lbl.innerText).eql('Select a value:');
    await t.expect(await visib.innerText).eql('Toggle disable/enable');
    await t.expect(await toggle.innerText).eql('Toggle widget');
    await t.expect(await getval.innerText).eql('Get value');
    await t.expect(await setval.innerText).eql('Set value to 5');
});
// test('Input buttons check', async t => {
//     page.browserscroll();
//     await t.switchToIframe('.demo-frame')
//     await t.click(up);
//     await t.expect(await setval.innerText).eql(1);
//     await t.click(down);
//     await t.expect(await setval.innerText).eql(0);
// })
test('Input Enabled and disable button check', async t => {
        page.browserscroll();
        await t.switchToIframe('.demo-frame')
        await t.click(visib);
        await t.expect(await input.hasAttribute('disabled')).ok();
        await t.click(visib);
        await t.expect(await input.hasAttribute('disabled')).notOk();
})
    test('Toggle widget check', async t => {
        page.browserscroll();
        await t.switchToIframe('.demo-frame')
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
    await t.switchToIframe('.demo-frame')
    await t.click(toggle);
    await t.expect(await input.hasAttribute('disabled')).notOk();
})
    test('Get value', async t =>{
        page.browserscroll();
        await t.switchToIframe('.demo-frame')
        .click(getval)
        .setNativeDialogHandler(() => true)
    })
    test('Set value', async t =>{
        page.browserscroll();
        await t.switchToIframe('.demo-frame')
        .click(setval);
        await t.expect(input.value).eql('5');
 })