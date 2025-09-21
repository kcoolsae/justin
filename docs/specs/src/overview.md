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

**Personal data** Full name, email address, country. This information is used to identify the
participant.

**Background** as a member of the community. This data is used by the task handling committee (THC) to set up working
groups.

**Preferences** Accommodation, dietary requirements, etc. This information is used by the host country to make
arrangements for the international workshop.

Important in this division is who has access to this data, the THC, the host, or both.

Note that the system treats the last two categories in a very similar way, so it is perfectly feasible to add
a category in the future.

{:.note}
The system was not used in 2025 because the host (South Korea) had already started implementing 
their own system to gather accommodation preferences. It is not clear whether the system will
be used in 2026 - we want to avoid using two different systems at the same time.

User types
---
We consider two types of user

* *Regular users*. Typically, they belong to the Bebras community
  and will use the system through subsequent years.
* *Temporary users*. (Find a better name?) Typically, they accompany
  a regular user and need to use the system only for one specific year to
  register accommodation preferences.

Regular users can be granted extra privileges (see below).

Accounts for temporary users will be *disabled* after an event.
Regular user accounts can also be disabled, e.g., when they have left
the Bebras community.

{:.note}
In 2025 only regular users will be supported by the system.

Privileges
---
Users can be granted certain privileges which allow them to perform certain actions. Privileges are granted
only for the running year and can be restricted to one data category.

**Basic** Can see/edit their own data.
Essentially everybody who is registered with the system has this privilege,

**Overview** Can see who of their country is participating in the workshop. 
Regular users have this privilege.

**View data** Can view/download the data for a *specific category*. Typically, all THC members
will be given this privilege for the category 'background' and workshop organisers from the host
country will be given this privilege for the category 'preferences'. (It is possible to grant this
privilige to a user, e.g., a board member, for more than one category.)

**Edit forms** Can edit the forms used to request information. Typically, one or two members of the THC
will be given this privilege for the category 'background' while one or two works shop organisers will be
given this privilege for the category 'preferences'.

**View personal data** Can see names, email addresses and countries for *all* users (and not only
for users of the same country).

**Register new users** (See also below) Can register new regular users
(i.e., send a registration invite - see below). 
Can also obtain a list of all registered users.
Distinguished between
only for their own country vs. for any country.

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
address with a link they can use to access the system. This link is valid for only a 
short amount of time (say, 20 minutes)
and can be used
only once. 

Once logged in, the session remains active until no actions have been performed for a certain
period of time (say 24 hours). Hence, the fact that the login link is only short-lived should not be a problem. 

{:.note}
Time will tell whether we should also provide an alternative way of authentication for users
that need frequent access (administrators) or for whom
emails from the system tend to arrive in their spam folder…

Registration
---

New users are added to the system by being sent a 'registration invite'
by someone with the correct privileges. (Note: this is not a formal
invite to participate in the international workshop!)

Regular users can only be invited by someone of the same country
with registration privileges, typically, the Bebras representative
of that country.
Temporary users can be invited by any registered regular user of that
same country. Temporary users can *not* themselves invite other people.
There are a few (admin) users that have the privilege
to invite people independent of country.

To invite a person, you enter their email address
in the system (and press a button). The system then sends an email to that person
with a link that allows them to complete their registration
(and enter their full name).

Language
--------
Only an English language user interface is provided.

{:.note}
This is important for technical reasons - internationalizing a web application which originally was not intended to be,
is an enormous amount of work. A single language application is much easier to program.


