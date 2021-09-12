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
const iframe = Selector(".demo-frame"); 
const lbl    = Selector('p').nth(0);
const input  = Selector('#datepicker');
const main   = Selector('#ui-datepicker-div');
const left   = Selector('#ui-datepicker-div span').withExactText('Prev');
const right  = Selector('#ui-datepicker-div span').withExactText('Next');
const date   = Selector('#ui-datepicker-div a').withExactText('21');
const monthPic = Selector('#ui-datepicker-div > div > div > .ui-datepicker-month');
const datePic  = Selector('#ui-datepicker-div a').withExactText('12');
const yearPic  = Selector('#ui-datepicker-div > div > div > .ui-datepicker-year');
test('Label Test',async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    const name = await lbl.innerText;
    await t.expect(lbl.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 })
    .expect(name).eql('Date: ');
})
test('Input Enter Date',async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    .click(input)
    await t.expect(input.getAttribute('type')).contains('text');
    await t
    .typeText(input, '12/07/2000')
    .expect(await monthPic.innerText).eql('July')
    .expect(await yearPic.innerText).eql('2000')
    .expect(await datePic.innerText).eql('12')
    .expect(await datePic.getAttribute('background-color')).contains('rgb(0 127 255)')
    .expect(await input.value).eql('12/07/2000');

})
test('Select Date',async t => {
    page.browserscroll();
    await t .switchToIframe(iframe)
    .expect(input.getAttribute('type')).eql('text')
    .click(input);
    await t.click(left)
    .expect(await monthPic.innerText).eql('September');
    await t.click(right)
    .expect(await monthPic.innerText).eql('August');
    await t.click(date) .wait(1000)  
    .expect(await input.value).contains('08/21/2021');
})