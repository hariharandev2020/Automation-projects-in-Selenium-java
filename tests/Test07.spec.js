import { Selector } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const datePicker  = Selector('a').withExactText('Datepicker');
    await t.click(datePicker);
})
const main   = Selector('#ui-datepicker-div');

test('Label Test',async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const label    = Selector('p').nth(0);
    const name = await label.innerText;
    await t.expect(label.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(name).eql('Date: ');
})
test('Input Enter Date',async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const input    = Selector('#datepicker');
    const monthPic = Selector('#ui-datepicker-div > div > div > span.ui-datepicker-month');
    const datePic  = Selector('#ui-datepicker-div a').withExactText('12');
    const yearPic  = Selector('#ui-datepicker-div > div > div > .ui-datepicker-year');
    await t
    .click(input)
    .typeText(await input, '07/12/2000')
    .pressKey('enter')
    await t.click(input)
    await t.expect(await monthPic.innerText).contains('July')
    .expect(await yearPic.innerText).contains('2000')
    .expect(await datePic.innerText).contains('12')
    // console.log(await datePic.getStyleProperty('background-color'));
    .expect(await datePic.getStyleProperty('background-color')).contains('rgb(0, 127, 255)')
    await t.expect(await input.value).contains('07/12/2000');
})
test('Select Date',async t => {
    page.browserscroll();
    await t .switchToIframe('.demo-frame');
    const monthPic = Selector('#ui-datepicker-div > div > div > .ui-datepicker-month');
    const input  = Selector('#datepicker');
    const left   = Selector('#ui-datepicker-div span').withExactText('Prev');
    const right  = Selector('#ui-datepicker-div span').withExactText('Next');
    const date   = Selector('#ui-datepicker-div a').withExactText('21');
    await t.expect(input.getAttribute('type')).eql('text')
    .click(input);
    await t.click(left)
    .expect(await monthPic.innerText).eql('September');
    await t.click(right)
    .expect(await monthPic.innerText).eql('August');
    await t.click(date)
    .expect(input.value).contains('09/21/2021');
})