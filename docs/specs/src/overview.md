Overview
===

See also:

* [Home page](index.md)

---

In order to organise the Bebras International Workshop, a lot of information is needed about the participants,
concerning their background as a member of the community and their preferences for (accommodation during) the workshop.
Traditionally, this information is gathered through several web-based forms (Google forms, say) that are filled in by
the participants in the weeks before the workshop.

This web application aims to streamline this process by

* using the same system from one year to the next,
* allowing data from earlier years to be reused,
* providing a single access point for questions related to tasks, accommodation, personal details, etc.

The data will be stored in a database, which can be consulted online or from which subsets can be extracted in the form
of Excel worksheets, by certain authorised users. Data remains stored over the years, so that participants need not fill
in the same information every year.

Data
----

The data stored falls into three different categories

**Personal data** Full name, email address, country, privileges (see below). This information is used to identify the
participant. (A participant can see who else of their country is participating in the workshop and in what role.)

**Background** as a member of the community. This data is used by the task handling committee (THC) to set up work
groups.

**Preferences** Accommodation, dietary requirements, etc. This information is used by the host country to make
arrangements for the international workshop.

Important in this division is who has access to this data, the THC, the host, or both.

Note that the system treats the last two categories in a very similar way, so it is perfectly feasible to add
a category in the future without a rewrite.

Privileges
---
Users can be granted certain privileges which allows them to perform certain actions. Privileges are granted
only for the running year and can be restricted to one data category.

**Basic** Can see/edit their own data. Can see who of their country is participating in the workshop. Essentially
everybody who is registered with the system has this privilege.

**View data** Can view/download the data for a *specific category*. Typically, all THC members
will be given this privilege for the category 'background' and workshop organisers from the host
country will be given this privilege for the category 'preferences'. (It is possible to grant this
privilige to a user, e.g., a board member, for more than one category.)

**Edit forms** Can edit the forms used to request information. Typically, one or two members of the THC
will be given this privilege for the category 'background' while one or two works shop organisers will be
given this privilege for the category 'preferences'.

**View personal data** Can see names, email addresses and countries for *all* users (and not only
for users of the same country).

**Register new users** (See also below) Can register new users or validate registrations. Possibly distinguished between
only for a specific country vs. for any country.

**Admin** Can assign/revoke privileges to/from users. Can reset the system between subsequent workshops.

{:.note}
Do we allow users with a given privilege to grant that privilege to others? E.g., can one work shop organiser grant the
rol of 'view host data' to
another person? Should we split the admin privilege into smaller parts?

Note that users must indicate each year whether they will participate in the task editing process (if not, they are
classified as *accompanying person*) and whether
they need accommodation. The latter will be the case for most participants, but as we keep a history of previous years,
the fact that a user is registered
with the system does not necessarily imply that they will participate in the workshop.

Authentication
---
As the web application will be used only a few times per year we would prefer not to use a password authentication
system, as most people tend to forget passwords for this type of application and need to click the 'forgot password'
link anyway. So we cut out the middle man:

To authenticate, people have to type in their e-mail address and click a submit button. This sends a mail to that email
address with a link they can use to access the system. This link is valid for a certain amount of time (say, 24 hours) and can be used
only once.

{:.note}
Should we provide an alternative way of authentication for users that need frequent access (administrators) or for whom
emails from the system
tend to arrive in their spam folder?

Registration
---

{:.note}
We see two different options for registration.

1 A special link is provided for first time registration. This leads to a page into which
new users can enter email address, full name and country. After submitting this page, it needs to
be confirmed that this registration is valid, by
* A registered user of the same country, or
* a user with a special *Registration* privilege. (e.g., a THC member)

This sends an email to the new user to confirm their access to these system.

2 Self-registration is not possible. Instead
* A registered user can register a new user *for the same country*,
* or a user with a *Registration* privilege can register anyone, independent of country

In this case only email address (and country) are entered in the system. The new user receives
a mail with a link to complete the registration (and enter their full name).

The above is a *lenient* version. There is also an option to be a bit *stricter*: not everyone from the
same country can validate registration of a new user (case 1) or register a new user (case 2). Instead, only a selected
number of people of that country can do so. (I.e., those having a special *Registration* privilege.)

{:.note}
**TODO** Select which method we shall use (1 or 2) and whether in the *strict* or in the *lenient* version.

Language
--------
Only an English language user interface is provided.

{:.note}
This is important for technical reasons - internationalizing a web application which originally was not intended to be,
is an enormous amount of work. On the other hand, a single language application is much easier to program.


