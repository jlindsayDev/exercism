import json

class RestAPI:
    def __init__(self, database=None):
        users = [] if not database else database['users']
        self.database = {'users': { u['name']: u for u in users }}

    @classmethod
    def unformat_user(cls, user):
        unformatted_user = {
            'name': user['name'],
            'balance': user['balance'],
            'owes': {},
        }

        for name, amount in user['owes'].items():
            if amount < 0:
                user['owed_by'][name] = -amount
        for name, amount in user['owed_by'].items():
            if amount < 0:
                user['owes'][name] = -amount

        return unformatted_user

    @classmethod
    def format_user(cls, user):
        formatted_user = {
            'name': user['name'],
            'balance': user['balance'],
            'owes': {},
            'owed_by': {},
        }

        for name, amount in user['owes'].items():
            if amount > 0:
                formatted_user['owes'][name] = amount
            elif amount < 0:
                formatted_user['owed_by'][name] = -amount

        return formatted_user


    def get(self, url, payload=None):
        payload = {} if not payload else json.loads(payload)

        # /users
        # input: {"users":["Adam","Bob"]}
        # output: {"users":<List of User objects for <users> (sorted by name)}
        if 'users' in url:
            if payload:
                users = payload['users']
            else:
                users = self.database['users'].keys()
            users = [u for u in self.database['users'].values() if u['name'] in users]
            return json.dumps({'users': sorted(users, key=lambda u: u['name'])})


    def post(self, url, payload=None):
        payload = {} if not payload else json.loads(payload)

        # /add
        # input: {"user":<name of new user (unique)>}
        # output: <User object for new user>
        if 'add' in url:
            user = {
                'name': payload['user'],
                'owes': {},
                'owed_by': {},
                'balance': 0.0,
            }
            self.database['users'][user['name']] = user
            return json.dumps(user)

        # /iou
        # input: {"lender":<name of lender>,"borrower":<name of borrower>,"amount":5.25}
        # output: {"users":<updated User objects for <lender> and <borrower> (sorted by name)>}
        elif 'iou' in url:
            lender = self.database['users'][payload['lender']]
            borrower = self.database['users'][payload['borrower']]

            lender['balance'] += payload['amount']
            borrower['balance'] -= payload['amount']

            # lender owes borrower
            if lender['owes'].get(borrower['name']):
                lender['owes'][borrower['name']] -= payload['amount']

                # lender no longer owes borrower
                if lender['owes'][borrower['name']] <= 0:
                    lender['owed_by'][borrower['name']] = -lender['owes'][borrower['name']]
                    del lender['owes'][borrower['name']]
                    if lender['owed_by'][borrower['name']] == 0:
                        del lender['owed_by'][borrower['name']]
            else:
                lender_owed = lender['owed_by'].get(borrower['name'], 0.0)
                lender['owed_by'][borrower['name']] = lender_owed + payload['amount']

            # borrower owed by lender
            if borrower['owed_by'].get(lender['name']):
                borrower['owed_by'][lender['name']] -= payload['amount']

                # borrower no longer owes lender
                if borrower['owed_by'][lender['name']] <= 0:
                    borrower['owes'][lender['name']] = -borrower['owed_by'][lender['name']]
                    del borrower['owed_by'][lender['name']]
                    if borrower['owes'][lender['name']] == 0:
                        del borrower['owes'][lender['name']]
            else:
                borrower_owes = borrower['owes'].get(lender['name'], 0.0)
                borrower['owes'][lender['name']] = borrower_owes + payload['amount']

            users = [lender, borrower]
            return json.dumps({'users': sorted(users, key=lambda u: u['name'])})
